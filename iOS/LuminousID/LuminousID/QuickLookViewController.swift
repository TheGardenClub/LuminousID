//
//  QuickLookViewController.swift
//  LuminousID
//
//  Created by Kevin Rau on 4/6/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit
import Firebase
import FirebaseAuth
import FirebaseDatabase

class QuickLookViewController: UIViewController {
    
    var speciesObsDictQL = [String:AnyObject]()
    var min = 0
    var hr = 0
    var ts = 0.0
    var species = ""
    var gpsLat = 0.0
    var gpsLong = 0.0
    var name =  ""
    var ref:FIRDatabaseReference?
    var user = FIRAuth.auth()?.currentUser
    var plantCodeQL = ""
    var speciesNameQL = ""
    var userNameQL = ""
    var datetimeQL = ""
    var dateQL = ""
    let filemgr = FileManager.default
    var fullImageName = ""
    var newPath = ""
    var accQL = 0.0
    @IBOutlet weak var quickLookImage: UIImageView!
    var photoImage: UIImage!
    
    @IBOutlet weak var commentsBox: UITextView!
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
    }
    @IBAction func AddObsButton(_ sender: Any) {
        DispatchQueue.main.asyncAfter(deadline: .now() + 1.25) {
            self.performSegue(withIdentifier: "addObsSegue", sender: nil)
        }
    }
    @IBAction func RetakePhotoButton(_ sender: Any) {
        navigationController?.popViewController(animated: true)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override var prefersStatusBarHidden : Bool {
        return true
    }
    
    override func viewDidAppear(_ animated: Bool) {
        if photoImage != nil {
            quickLookImage.image = photoImage
        }
    }
    
    override func viewDidLoad() {
        print(min)
        print(hr)
        print(ts)
        print(gpsLat)
        print(gpsLong)
        print(plantCodeQL)
        print(speciesNameQL)
        
        
        
        ref = FIRDatabase.database().reference()
        name = "\(Int(ts))" + "_" + (FIRAuth.auth()?.currentUser?.uid)!
        fullImageName = name + ".jpg"
        datetimeQL = dateQL
        print (datetimeQL)
        print(name)
        let fileURL = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent(fullImageName)
        print("1")
        let jpgImageData = UIImageJPEGRepresentation(photoImage, 1.0)
        print("2")
        do {
            try jpgImageData?.write(to: fileURL, options: .atomic)
            print("3")
        }
        catch{
            print("mistakes were made")
        }
        print("4)")
        print (fileURL)
        super.viewDidLoad()
        
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(CreateUserViewController.dismissKeyboard))
        
        view.addGestureRecognizer(tap)
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "addObsSegue"{
            print(name)
            let MyObsVC = segue.destination as! MyObservationsViewController
            MyObsVC.datetime = datetimeQL
            MyObsVC.gps_lat = gpsLat
            MyObsVC.gps_long = gpsLong
            MyObsVC.is_synced = false
            MyObsVC.is_verified = 0
            MyObsVC.plant_code = plantCodeQL
            MyObsVC.species_name = speciesNameQL
            MyObsVC.datetime = datetimeQL
            MyObsVC.comment = commentsBox.text
            MyObsVC.photoName = name
            MyObsVC.fullPhotoName = fullImageName
            MyObsVC.gpsAccuracy = accQL
        }
        else{
            print("whoops, something went wrong")
        }
        
    }
    
    func dismissKeyboard(){
        view.endEditing(true)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
