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
    
    
    var min = 0
    var hr = 0
    var ts = 7.465433456
    var species = ""
    var gpsLat = 0.0
    var gpsLong = 0.0
    var name =  ""
    var ref:FIRDatabaseReference?
    
    
    @IBOutlet weak var quickLookImage: UIImageView!
    var photoImage: UIImage!
    
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override var prefersStatusBarHidden : Bool {
        return true
    }
    
    
    override func viewDidLoad() {
        ref = FIRDatabase.database().reference()
        name = "\(ts)" + "_" + (FIRAuth.auth()?.currentUser?.uid)!
        print(name)
        super.viewDidLoad()
        if photoImage != nil {
            quickLookImage.image = photoImage
        }
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
