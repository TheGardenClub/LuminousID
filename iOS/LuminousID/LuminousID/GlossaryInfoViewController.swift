//
//  GlossaryInfoViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/12/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class GlossaryInfoViewController: UIViewController {
    
    var imageName = ""
    var imageFile = ""
    
    @IBOutlet weak var navBar: UINavigationItem!
    @IBOutlet weak var GlossaryInfoImageView: UIImageView!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        GlossaryInfoImageView.image = UIImage(named: imageFile)
        self.navBar.title = (imageName)
        // Do any additional setup after loading the view.
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
