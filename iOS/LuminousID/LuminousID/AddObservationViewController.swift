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

class AddObservationViewController: UIViewController {
    
    var speciesObsDict = [String:AnyObject]()
    
    @IBOutlet weak var cameraView: UIView!
    
    let captureSession = AVCaptureSession()
    
    var captureDevice: AVCaptureDevice?
    
    var previewLayer : AVCaptureVideoPreviewLayer?
    
    var frontCamera: Bool = false
    
    var stillImageOutput: AVCaptureStillImageOutput = AVCaptureStillImageOutput()
    
    
    override func viewDidLoad() {
        print (speciesObsDict["species_name"] as! String)
        super.viewDidLoad()
        
        captureSession.sessionPreset = AVCaptureSessionPresetPhoto
        frontCamera(frontCamera)
        
        if captureDevice != nil{
            beginSession()
        }

        // Do any additional setup after loading the view.
    }
    
    func beginSession(){
        
        previewLayer = AVCaptureVideoPreviewLayer(session: captureSession)
        self.cameraView.layer.addSublayer(previewLayer!)
        previewLayer?.frame = self.cameraView.layer.bounds
        previewLayer?.videoGravity = AVLayerVideoGravityResizeAspectFill
        captureSession.startRunning()
        stillImageOutput.outputSettings = [AVVideoCodecKey : AVVideoCodecJPEG]
        
        if captureSession.canAddOutput(stillImageOutput){
            captureSession.addOutput(stillImageOutput)
        }
    }
    
    func frontCamera(_ front: Bool){
        
        let devices = AVCaptureDevice.devices()
        do{
            try captureSession.removeInput(AVCaptureDeviceInput(device: captureDevice))
            
        }catch{
            print("error")
            
        }
        
        for device in devices!{
            
            if(((device as AnyObject).hasMediaType(AVMediaTypeVideo))){
                
                if front{
                    if (device as AnyObject).position == AVCaptureDevicePosition.front {
                        captureDevice = device as? AVCaptureDevice
                        
                        do{
                            try captureSession.addInput(AVCaptureDeviceInput(device: captureDevice))
                            
                            
                        }catch{
                            
                        }
                        break
                    }
                    
                }else{
                    if (device as AnyObject).position == AVCaptureDevicePosition.back {
                        captureDevice = device as? AVCaptureDevice
                        
                        do{
                            try captureSession.addInput(AVCaptureDeviceInput(device: captureDevice))
                            
                            
                        }catch{
                            
                        }
                        break
                    }
                    
                }
            }
        }
    }
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func capturePicture(_ sender: Any) {
        
        if let videoConnection = stillImageOutput.connection(withMediaType: AVMediaTypeVideo){
            stillImageOutput.captureStillImageAsynchronously(from: videoConnection, completionHandler: { (imageDataSampleBuffer, error) in
                let imageData = AVCaptureStillImageOutput.jpegStillImageNSDataRepresentation(imageDataSampleBuffer)
                let image = UIImage(data: imageData!)

                self.cameraView.backgroundColor = UIColor(patternImage: image!)
                let imageView = UIImageView(image: image)
                imageView.frame = self.cameraView.frame
                self.cameraView.addSubview(imageView)
                
            
                
            })
        }
        
    }
    
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
