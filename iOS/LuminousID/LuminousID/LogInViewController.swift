//
//  LogInViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 2/24/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

/*
    This view manages logging in, continuing as a guest, and navigating to the create account view.
 */

import UIKit
import Firebase
import FirebaseAuth
import FirebaseDatabase

class LogInViewController: UIViewController {

    @IBOutlet weak var loginEmail: UITextField!
    @IBOutlet weak var loginPass: UITextField!
    
    /*
        Once the view loads, if there is a user signed in, it skips this view.
     */
    override func viewDidAppear(_ animated: Bool) {
        if FIRAuth.auth()?.currentUser?.email != nil{
            self.performSegue(withIdentifier: "toMainMenuFromLogin", sender: nil)
        }
    }
    /*
        Loads a tap recognizer to dismiss keyboard when the user taps on the screen
     */
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(LogInViewController.dismissKeyboard))
        
        view.addGestureRecognizer(tap)
        
    }
    /*
        Dismisses Keyboard
     */
    func dismissKeyboard(){
        view.endEditing(true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    /*
        Skips signing in, and segues to main menu view
     */
    @IBAction func continueAsGuest(_ sender: Any) {
        self.performSegue(withIdentifier: "toMainMenuFromLogin", sender: nil)
    }
    /*
        On button press, check to make sure the credentials are correct and sign the user in. Then it segues to the main menu view. It also handles error messages for incorect credentials and improperly formatted credentials.
     */
    @IBAction func loginUser(_ sender: Any) {
        if self.loginEmail.text == "" || self.loginPass.text == ""
        {
            let alertController = UIAlertController(title: "Oops!", message: "Please enter an email and password.", preferredStyle: .alert)
            alertController.addAction(UIAlertAction(title: "OK", style: .cancel, handler: nil))
            self.present(alertController, animated: true, completion: nil)
            
        }
        else
        {
            FIRAuth.auth()?.signIn(withEmail: loginEmail.text!, password: loginPass.text!) { (user, error) in
                // ...
                if error == nil
                {
                    self.performSegue(withIdentifier: "toMainMenuFromLogin", sender: nil)
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
