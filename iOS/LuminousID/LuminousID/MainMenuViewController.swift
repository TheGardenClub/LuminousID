//
//  MainMenuViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 2/25/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit
import Firebase
import FirebaseAuth
import FirebaseDatabase


class MainMenuViewController: UIViewController {
    
    var userNamePath = ""
    var ref:FIRDatabaseReference?
    var user = FIRAuth.auth()?.currentUser
    @IBOutlet var logOutButton: UIButton!
    @IBOutlet var userEmailLabel: UILabel!
    override func viewDidLoad() {
        ref = FIRDatabase.database().reference()
        if FIRAuth.auth()?.currentUser?.email != nil
        {
            self.userEmailLabel.text = FIRAuth.auth()?.currentUser?.email
        }
        else
        {
            self.userEmailLabel.text = "Guest"
            logOutButton.setTitle("Log In", for: .normal)
        }
        /*

        FIRDatabase.database().reference().child(userNamePath).observeSingleEvent(of: .value, with: {(snap) in
            if let snapDict = snap.value as? [String:AnyObject]{
                self.username = snapDict["username"] as? String
            }
            else{
                self.username = ""
                
            }
            print (self.username)
        })
        */
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    @IBAction func logOut(_ sender: Any) {
        try! FIRAuth.auth()?.signOut()
        self.performSegue(withIdentifier: "toLoginFromMainMenu", sender: nil)
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
