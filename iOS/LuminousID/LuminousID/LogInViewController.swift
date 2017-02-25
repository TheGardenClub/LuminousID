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
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func loginUser(_ sender: Any) {
        FIRAuth.auth()?.signIn(withEmail: loginEmail.text!, password: loginPass.text!) { (user, error) in
            // ...
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
