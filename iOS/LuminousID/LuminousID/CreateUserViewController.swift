//
//  CreateUserViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 2/24/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

/*
    This view manages creating an account, segueing to the log in view, and segueing to the main menu view
 */

import UIKit
import Firebase
import FirebaseAuth
import FirebaseDatabase
class CreateUserViewController: UIViewController {

    @IBOutlet weak var userEmail: UITextField!
    @IBOutlet weak var userPass: UITextField!
    @IBOutlet weak var confirmPass: UITextField!
    @IBOutlet weak var userName: UITextField!
    
    var userNamePath = ""
    var emailPath = ""
    var ref:FIRDatabaseReference?
    /*
        Grabs a reference to the firebase database, and initialize the keyboard tap recognizer
     */
    override func viewDidLoad() {
        super.viewDidLoad()
        ref = FIRDatabase.database().reference()
               // Do any additional setup after loading the view.
        
        
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(CreateUserViewController.dismissKeyboard))
        
        view.addGestureRecognizer(tap)
        
    }
    
    /*
        Dismisses keyboard
     */
    
    func dismissKeyboard(){
        view.endEditing(true)
    }


    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    /*
        Checks the formatting of the users credentials, and if they are okay, creates the account in firebase and segues to the main menu screen. The is_researcher, username, and email fields are also updated in firebase. If the credentials are improperly formatted, it displays a pop-up error message.
     */
    
    @IBAction func createUser(_ sender: Any) {
        if self.userEmail.text == "" || self.userPass.text == "" || self.userName.text == ""
        {
            let alertController = UIAlertController(title: "Oops!", message: "Please enter an email, a username, and a password.", preferredStyle: .alert)
            alertController.addAction(UIAlertAction(title: "OK", style: .cancel, handler: nil))
            self.present(alertController, animated: true, completion: nil)
        }
        else if self.userPass.text != self.confirmPass.text
        {
            let alertController = UIAlertController(title: "Oops!", message: "Passwords don't match. Please try again.", preferredStyle: .alert)
            alertController.addAction(UIAlertAction(title: "OK", style: .cancel, handler: nil))
            self.present(alertController, animated: true, completion: nil)
            self.userPass.text = ""
            self.confirmPass.text = ""
        }
            
        else
        {
            FIRAuth.auth()?.createUser(withEmail: userEmail.text!, password: userPass.text!) { (user, error) in
                
                if error == nil
                {
                    self.userNamePath = "speciesid/accounts/" + (user?.uid)! + "/username"
                    self.emailPath = "speciesid/accounts/" + (user?.uid)! + "/email"
                    self.ref?.child("speciesid").child("accounts").child((user?.uid)!).setValue(["researcher": 0])
                    self.ref?.child(self.userNamePath).setValue(self.userName.text)
                    self.ref?.child(self.emailPath).setValue(self.userEmail.text)
                    self.performSegue(withIdentifier: "toMainMenuFromCreate", sender: nil)
                }
                else
                {
                    let alertController = UIAlertController(title: "Oops!", message: error?.localizedDescription, preferredStyle: .alert)
                    alertController.addAction(UIAlertAction(title: "OK", style: .cancel, handler: nil))
                    self.present(alertController, animated: true, completion: nil)
                }
            }
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
