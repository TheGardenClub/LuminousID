//
//  SpeciesInfoViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 3/6/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class SpeciesInfoViewController: UIViewController {


    @IBOutlet weak var Label1: UILabel!
    
    @IBOutlet weak var Label2: UILabel!
    
    @IBOutlet weak var Label3: UILabel!
    
    @IBOutlet weak var Label4: UILabel!
    
    @IBOutlet weak var Label5: UILabel!
    
    @IBOutlet weak var Label6: UILabel!
    
    @IBOutlet weak var Label7: UILabel!
    
    @IBOutlet weak var Label8: UILabel!
    
    @IBOutlet weak var Label9: UILabel!
    
    @IBOutlet weak var Label10: UILabel!
    
    @IBOutlet weak var Label11: UILabel!
    
    @IBOutlet weak var Label12: UILabel!
    
    @IBOutlet weak var Label13: UILabel!
    
    @IBOutlet weak var Label14: UILabel!
    
    @IBOutlet weak var titleLabel: UILabel!
    
    
    
    var speciesDict = [[String:AnyObject]]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if (speciesDict[0]["growth_form"] as! String) == "forb"
        {
            Label1.text = "Species Name: " + (speciesDict[0]["species_name"] as! String)
            Label2.text = "Family: " + (speciesDict[0]["family_name"] as! String)
            Label3.text = "Growth Form: " + (speciesDict[0]["growth_form"] as! String)
            Label4.text = "Leaf Shape: " + (speciesDict[0]["leaf_shape_filter"] as! String)
            Label5.text = "Leaf Arrangement: " + (speciesDict[0]["leaf_arrangement"] as! String)
            Label6.text = "Petal Number: " + (speciesDict[0]["petal_number"] as! String)
            Label7.text = "Flower Shape: " + (speciesDict[0]["flower_shape"] as! String)
            Label8.text = "Synonyms: " + (speciesDict[0]["synonyms"] as! String)
            Label9.text = "Flower Color: " + (speciesDict[0]["flower_color"] as! String)
            Label10.text = "Common Name: " + (speciesDict[0]["common_name"] as! String)
            Label11.text = "Habitat: " + (speciesDict[0]["habitat"] as! String)
            /*
             Label12.text = "Photo Credit: " + (speciesDict[0]["photo_credit"] as! String)
             */
            Label12.text = "Photo Credit: Brian Larson"
            Label13.text = "Notes: " + (speciesDict[0]["notes"] as! String)
            titleLabel.text = (speciesDict[0]["species_name"] as! String)
        }
        else
        {
            Label1.text = "This is broken"
        }
        
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
