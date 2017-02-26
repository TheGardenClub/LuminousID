//
//  LogInViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 2/24/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit
import Firebase
import FirebaseAuth
import FirebaseDatabase

class LogInViewController: UIViewController {

    @IBOutlet weak var loginEmail: UITextField!
    @IBOutlet weak var loginPass: UITextField!
    override func viewDidAppear(_ animated: Bool) {
        if FIRAuth.auth()?.currentUser?.email != nil{
            self.performSegue(withIdentifier: "toMainMenuFromLogin", sender: nil)
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func continueAsGuest(_ sender: Any) {
        self.performSegue(withIdentifier: "toMainMenuFromLogin", sender: nil)
    }
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
