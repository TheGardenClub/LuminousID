//
//  QuickLookViewController.swift
//  LuminousID
//
//  Created by Kevin Rau on 4/6/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class QuickLookViewController: UIViewController {
    
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
        super.viewDidLoad()
        if photoImage != nil {
            quickLookImage.image = photoImage
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func closeQuickLook(_ sender: AnyObject) {
        dismiss(animated: true, completion: nil)
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
