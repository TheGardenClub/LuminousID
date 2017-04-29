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
    var dateQL = Date()
    let filemgr = FileManager.default
    var fullImageName = ""
    var newPath = ""
    @IBOutlet weak var quickLookImage: UIImageView!
    var photoImage: UIImage!
    
    @IBOutlet weak var commentsBox: UITextView!
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
    }
    @IBAction func AddObsButton(_ sender: Any) {
        performSegue(withIdentifier: "addObsSegue", sender: nil)
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
        fullImageName = name + ".jpg"
        
        
        ref = FIRDatabase.database().reference()
        name = "\(ts)" + "_" + (FIRAuth.auth()?.currentUser?.uid)!
        datetimeQL = "\(dateQL)"
        print (datetimeQL)
        print(name)
        let dirPaths = filemgr.urls(for: .documentDirectory, in: .userDomainMask)
        let docsDir = dirPaths[0].path
        let jpgImageData = UIImageJPEGRepresentation(photoImage, 1.0)
        super.viewDidLoad()
        
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(CreateUserViewController.dismissKeyboard))
        
        view.addGestureRecognizer(tap)
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "addObsSegue"{
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
