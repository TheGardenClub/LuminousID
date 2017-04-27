//
//  AddObservationViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 3/23/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//


import UIKit
import Firebase
import FirebaseAuth
import FirebaseDatabase
import AVFoundation
import Photos
import CoreLocation
import GeoFire

class AddObservationViewController: UIViewController, CLLocationManagerDelegate {
    var speciesObsDict = [String:AnyObject]()
    let captureSession = AVCaptureSession()
    let imageOutput = AVCaptureStillImageOutput()
    var previewLayer: AVCaptureVideoPreviewLayer!
    var activeInput: AVCaptureDeviceInput!
    var lat = 0.0
    var long = 0.0
    var date = Date()
    var calendar = Calendar.current
    var hour:Int = 0
    var minutes:Int = 0
    var timestamp = 0.0
    
    
    let manager = CLLocationManager()
    
    var mapHasCenteredOnce = false
    var geoFire: GeoFire!
    var geoFireRef: FIRDatabaseReference!
    
    

    var focusMarker: UIImageView!
    var exposureMarker: UIImageView!
    var resetMarker: UIImageView!
    fileprivate var adjustingExposureContext: String = ""
    
    var ref:FIRDatabaseReference?
    


    
    @IBOutlet weak var camPreview: UIView!
    @IBOutlet weak var thumbnail: UIButton!
    @IBOutlet weak var flashLabel: UILabel!
    
    
    override func viewWillAppear(_ animated: Bool) {
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        super.viewWillAppear(animated)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        super.viewWillDisappear(animated)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setupSession()
        setupPreview()
        startSession()
        
        geoFireRef = FIRDatabase.database().reference()
        geoFire = GeoFire(firebaseRef: geoFireRef)
        manager.delegate = self
        manager.desiredAccuracy = kCLLocationAccuracyBest
        manager.requestWhenInUseAuthorization()
        manager.startUpdatingLocation()
        hour = calendar.component(.hour, from: date)
        minutes = calendar.component(.minute, from: date)
        print(date)
        print(hour)
        print(minutes)
        
        print (speciesObsDict["species_name"] as! String)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    override var prefersStatusBarHidden : Bool {
        return true
    }
    
    // MARK: - Setup session and preview

    func setupSession() {
        captureSession.sessionPreset = AVCaptureSessionPresetHigh
        let camera = AVCaptureDevice.defaultDevice(withMediaType: AVMediaTypeVideo)
        
        do {
            let input = try AVCaptureDeviceInput(device: camera)
            if captureSession.canAddInput(input) {
                captureSession.addInput(input)
                activeInput = input
            }
        } catch {
            print("Error setting device input: \(error)")
        }
        
        imageOutput.outputSettings = [AVVideoCodecKey : AVVideoCodecJPEG]
        if captureSession.canAddOutput(imageOutput) {
            captureSession.addOutput(imageOutput)
        }
        
    }
    
    func setupPreview() {
        // Configure previewLayer
        previewLayer = AVCaptureVideoPreviewLayer(session: captureSession)
        previewLayer.frame = camPreview.bounds
        previewLayer.videoGravity = AVLayerVideoGravityResizeAspectFill
        camPreview.layer.addSublayer(previewLayer)
        
        // Attach tap recognizer for focus & exposure.
        let tapForFocus = UITapGestureRecognizer(target: self, action: #selector(AddObservationViewController.tapToFocus(_:)))
        tapForFocus.numberOfTapsRequired = 1
        
        let tapForExposure = UITapGestureRecognizer(target: self, action: #selector(AddObservationViewController.tapToExpose(_:)))
        tapForExposure.numberOfTapsRequired = 2
        
        let tapForReset = UITapGestureRecognizer(target: self, action: #selector(AddObservationViewController.resetFocusAndExposure))
        tapForReset.numberOfTapsRequired = 2
        tapForReset.numberOfTouchesRequired = 2
        
        camPreview.addGestureRecognizer(tapForFocus)
        camPreview.addGestureRecognizer(tapForExposure)
        camPreview.addGestureRecognizer(tapForReset)
        tapForFocus.require(toFail: tapForExposure)
        
        // Create marker views.
        focusMarker = imageViewWithImage("Focus_Point")
        exposureMarker = imageViewWithImage("Exposure_Point")
        resetMarker = imageViewWithImage("Reset_Point")
        camPreview.addSubview(focusMarker)
        camPreview.addSubview(exposureMarker)
        camPreview.addSubview(resetMarker)
    }
    
    func startSession() {
        if !captureSession.isRunning {
            videoQueue().async {
                self.captureSession.startRunning()
            }
        }
    }
    
    func stopSession() {
        if captureSession.isRunning {
            videoQueue().async {
                self.captureSession.stopRunning()
            }
        }
    }
    
    func videoQueue() -> DispatchQueue {
        return DispatchQueue.global(priority: DispatchQueue.GlobalQueuePriority.default)
    }
    
    // MARK: - Configure
    @IBAction func switchCameras(_ sender: AnyObject) {
        // Make sure the device has more than 1 camera.
        if AVCaptureDevice.devices(withMediaType: AVMediaTypeVideo).count > 1 {
            // Check which position the active camera is.
            var newPosition: AVCaptureDevicePosition!
            if activeInput.device.position == AVCaptureDevicePosition.back {
                newPosition = AVCaptureDevicePosition.front
            } else {
                newPosition = AVCaptureDevicePosition.back
            }
            
            // Get camera at new position.
            var newCamera: AVCaptureDevice!
            let devices = AVCaptureDevice.devices(withMediaType: AVMediaTypeVideo)
            for device in devices! {
                if (device as AnyObject).position == newPosition {
                    newCamera = device as! AVCaptureDevice
                }
            }
            
            // Create new input and update capture session.
            do {
                let input = try AVCaptureDeviceInput(device: newCamera)
                captureSession.beginConfiguration()
                // Remove input for active camera.
                captureSession.removeInput(activeInput)
                // Add input for new camera.
                if captureSession.canAddInput(input) {
                    captureSession.addInput(input)
                    activeInput = input
                } else {
                    captureSession.addInput(activeInput)
                }
                captureSession.commitConfiguration()
            } catch {
                print("Error switching cameras: \(error)")
            }
        }
    }
    
    // MARK: Focus Methods
    func tapToFocus(_ recognizer: UIGestureRecognizer) {
        if activeInput.device.isFocusPointOfInterestSupported {
            let point = recognizer.location(in: camPreview)
            let pointOfInterest = previewLayer.captureDevicePointOfInterest(for: point)
            showMarkerAtPoint(point, marker: focusMarker)
            focusAtPoint(pointOfInterest)
        }
    }
    
    func focusAtPoint(_ point: CGPoint) {
        let device = activeInput.device
        // Make sure the device supports focus on POI and Auto Focus.
        if (device?.isFocusPointOfInterestSupported)! &&
            (device?.isFocusModeSupported(AVCaptureFocusMode.autoFocus))! {
            do {
                try device?.lockForConfiguration()
                device?.focusPointOfInterest = point
                device?.focusMode = AVCaptureFocusMode.autoFocus
                device?.unlockForConfiguration()
            } catch {
                print("Error focusing on POI: \(error)")
            }
        }
    }
    
    // MARK: Exposure Methods
    func tapToExpose(_ recognizer: UIGestureRecognizer) {
        if activeInput.device.isExposurePointOfInterestSupported {
            let point = recognizer.location(in: camPreview)
            let pointOfInterest = previewLayer.captureDevicePointOfInterest(for: point)
            showMarkerAtPoint(point, marker: exposureMarker)
            exposeAtPoint(pointOfInterest)
        }
    }
    
    func exposeAtPoint(_ point: CGPoint) {
        let device = activeInput.device
        if (device?.isExposurePointOfInterestSupported)! &&
            (device?.isExposureModeSupported(AVCaptureExposureMode.continuousAutoExposure))! {
            do {
                try device?.lockForConfiguration()
                device?.exposurePointOfInterest = point
                device?.exposureMode = AVCaptureExposureMode.continuousAutoExposure
                
                if (device?.isExposureModeSupported(AVCaptureExposureMode.locked))! {
                    device?.addObserver(self,
                                        forKeyPath: "adjustingExposure",
                                        options: NSKeyValueObservingOptions.new,
                                        context: &adjustingExposureContext)
                    
                    device?.unlockForConfiguration()
                }
            } catch {
                print("Error exposing on POI: \(error)")
            }
        }
    }
    
    override func observeValue(forKeyPath keyPath: String?,
                               of object: Any?,
                               change: [NSKeyValueChangeKey : Any]?,
                               context: UnsafeMutableRawPointer?) {
        
        if context == &adjustingExposureContext {
            let device = object as! AVCaptureDevice
            if !device.isAdjustingExposure &&
                device.isExposureModeSupported(AVCaptureExposureMode.locked) {
                (object as AnyObject).removeObserver(self,
                                                     forKeyPath: "adjustingExposure",
                                                     context: &adjustingExposureContext)
                
                DispatchQueue.main.async(execute: { () -> Void in
                    do {
                        try device.lockForConfiguration()
                        device.exposureMode = AVCaptureExposureMode.locked
                        device.unlockForConfiguration()
                    } catch {
                        print("Error exposing on POI: \(error)")
                    }
                })
                
            }
        } else {
            super.observeValue(forKeyPath: keyPath,
                               of: object,
                               change: change,
                               context: context)
        }
    }
    
    // MARK: Reset Focus and Exposure
    func resetFocusAndExposure() {
        let device = activeInput.device
        let focusMode = AVCaptureFocusMode.continuousAutoFocus
        let exposureMode = AVCaptureExposureMode.continuousAutoExposure
        
        let canResetFocus = (device?.isFocusPointOfInterestSupported)! &&
            (device?.isFocusModeSupported(focusMode))!
        let canResetExposure = (device?.isExposurePointOfInterestSupported)! &&
            (device?.isExposureModeSupported(exposureMode))!
        
        let center = CGPoint(x: 0.5, y: 0.5)
        
        if canResetFocus || canResetExposure {
            let markerCenter = previewLayer.pointForCaptureDevicePoint(ofInterest: center)
            showMarkerAtPoint(markerCenter, marker: resetMarker)
        }
        
        do {
            try device?.lockForConfiguration()
            if canResetFocus {
                device?.focusMode = focusMode
                device?.focusPointOfInterest = center
            }
            if canResetExposure {
                device?.exposureMode = exposureMode
                device?.exposurePointOfInterest = center
            }
            
            device?.unlockForConfiguration()
        } catch {
            print("Error resetting focus & exposure: \(error)")
        }
    }
    
    // MARK: Flash Modes
    @IBAction func setFlashMode(_ sender: AnyObject) {
        
        let device = AVCaptureDevice.defaultDevice(withMediaType: AVMediaTypeVideo)
        if (device?.hasTorch)! {
            do {
                try device?.lockForConfiguration()
                if (device?.torchMode == AVCaptureTorchMode.on) {
                    device?.torchMode = AVCaptureTorchMode.off
                } else {
                    do {
                        try device?.setTorchModeOnWithLevel(1.0)
                    } catch {
                        print(error)
                    }
                }
                device?.unlockForConfiguration()
            } catch {
                print(error)
            }
        }
        
        
        
        
        
    }
    
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        
        let location = locations[0]
        
        let span:MKCoordinateSpan = MKCoordinateSpanMake(0.01, 0.01)
        let myLocation:CLLocationCoordinate2D = CLLocationCoordinate2DMake(location.coordinate.latitude, location.coordinate.longitude)
        lat = myLocation.latitude
        long = myLocation.longitude

        
    }
    
    // MARK: - Capture photo
    @IBAction func capturePhoto(sender: AnyObject) {
        let connection = imageOutput.connection(withMediaType: AVMediaTypeVideo)
        let currentLat = self.lat
        let currentLong = self.long
        timestamp = NSDate().timeIntervalSince1970
        print(timestamp)
        print(currentLat)
        print(currentLong)
        
        if (connection?.isVideoOrientationSupported)! {
            connection?.videoOrientation = currentVideoOrientation()
        }
        
        imageOutput.captureStillImageAsynchronously (from: connection) {
            (sampleBuffer: CMSampleBuffer?, error: Error?) -> Void in
            if sampleBuffer != nil {
                
                let imageData = AVCaptureStillImageOutput.jpegStillImageNSDataRepresentation(sampleBuffer)
                let image = UIImage(data: imageData!)

                
                self.savePhotoToLibrary(image: image!)
                

                
                
            } else {
                print("Error capturing photo: \(String(describing: error?.localizedDescription))")
            }
        }
        performSegue(withIdentifier: "ShowTakenPic", sender: nil)
        
    }
    


        
    
    // MARK: - Helpers
    func savePhotoToLibrary(image: UIImage) {
        let photoLibrary = PHPhotoLibrary.shared()
        photoLibrary.performChanges({
            PHAssetChangeRequest.creationRequestForAsset(from: image)
        }) { (success: Bool, error: Error?) -> Void in
            if success {
                // Set thumbnail
                self.setPhotoThumbnail(image)
            } else {
                print("Error writing to photo library: \(error!.localizedDescription)")
            }
        }
    }
    

    
    
    func setPhotoThumbnail(_ image: UIImage) {
        DispatchQueue.main.async { () -> Void in
            self.thumbnail.setBackgroundImage(image, for: UIControlState())
            self.thumbnail.layer.borderColor = UIColor.white.cgColor
            self.thumbnail.layer.borderWidth = 1.0
        }
    }
    
    
    func imageViewWithImage(_ name: String) -> UIImageView {
        let view = UIImageView()
        let image = UIImage(named: name)
        view.image = image
        view.sizeToFit()
        view.isHidden = true
        
        return view
    }
    
    func showMarkerAtPoint(_ point: CGPoint, marker: UIImageView) {
        marker.center = point
        marker.isHidden = false
        
        UIView.animate(withDuration: 0.15,
                       delay: 0.0,
                       options: UIViewAnimationOptions(),
                       animations: { () -> Void in
                        marker.layer.transform = CATransform3DMakeScale(0.5, 0.5, 1.0)
        }) { (Bool) -> Void in
            let delay = 0.5
            let popTime = DispatchTime.now() + Double(Int64(delay * Double(NSEC_PER_SEC))) / Double(NSEC_PER_SEC)
            DispatchQueue.main.asyncAfter(deadline: popTime, execute: { () -> Void in
                marker.isHidden = true
                marker.transform = CGAffineTransform.identity
            })
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "QuickLookSegue" {
            let quickLook = segue.destination as! QuickLookViewController
            
            if let image = thumbnail.backgroundImage(for: UIControlState()) {
                quickLook.photoImage = image
            } else {
                quickLook.photoImage = UIImage(named: "Obs")
            }
        }else if segue.identifier == "ShowTakenPic"{
            let QuickLookVC = segue.destination as! QuickLookViewController
            QuickLookVC.min = minutes
            QuickLookVC.hr = hour
            QuickLookVC.gpsLat = lat
            QuickLookVC.gpsLong = long
            QuickLookVC.ts = timestamp
            let image = thumbnail.backgroundImage(for: UIControlState())
            QuickLookVC.photoImage = image
        }

    }
    
    func currentVideoOrientation() -> AVCaptureVideoOrientation {
        var orientation: AVCaptureVideoOrientation
        
        switch UIDevice.current.orientation {
        case .portrait:
            orientation = AVCaptureVideoOrientation.portrait
        case .landscapeRight:
            orientation = AVCaptureVideoOrientation.landscapeLeft
        case .portraitUpsideDown:
            orientation = AVCaptureVideoOrientation.portraitUpsideDown
        default:
            orientation = AVCaptureVideoOrientation.landscapeRight
        }
        
        return orientation
    }
    
    func randomFloat(from:CGFloat, to:CGFloat) -> CGFloat {
        let rand:CGFloat = CGFloat(Float(arc4random()) / 0xFFFFFFFF)
        return (rand) * (to - from) + from
    }
    
    func randomInt(_ n: Int) -> Int {
        return Int(arc4random_uniform(UInt32(n)))
    }
    
    @IBAction func closeQuickLook(_ sender: AnyObject) {
        /*
        dismiss(animated: true, completion: nil)
        */
        self.navigationController?.popViewController(animated: true)
    }
    

    
    
    
}



