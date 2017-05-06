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


/*
    This view serves as the main menu of the app. It handles segueing to the Field Guide, About, My Observations, and Glossary Views.
 */


class MainMenuViewController: UIViewController {
    
    var userNamePath = ""
    var ref:FIRDatabaseReference?
    var user = FIRAuth.auth()?.currentUser
    @IBOutlet var logOutButton: UIButton!
    @IBOutlet var userEmailLabel: UILabel!
    
    /*
        Segues to My Observations view if there is a user signed in, and displays a message if not.
     */
    
    @IBAction func MyObsButton(_ sender: Any) {
        if FIRAuth.auth()?.currentUser?.email != nil
        {
            performSegue(withIdentifier: "toMyObsFromMenu", sender: nil)
        }
        else
        {
            let alertController = UIAlertController(title: "Oops!", message: "You must be signed in to access My Observations.", preferredStyle: .alert)
            alertController.addAction(UIAlertAction(title: "OK", style: .cancel, handler: nil))
            self.present(alertController, animated: true, completion: nil)
        }
    }
    
    /*
        Checks if a user is signed in, and displayed their email if they are, and Guest if they aren't
     */
    
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
    
    
    /*
        Logs the user out and segues back to log in view
     */
    
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
