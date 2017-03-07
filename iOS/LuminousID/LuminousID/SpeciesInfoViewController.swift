//
//  SpeciesInfoViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 3/6/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class SpeciesInfoViewController: UIViewController {

    @IBOutlet weak var speciesLabel: UILabel!
    @IBOutlet weak var familyLabel: UILabel!
    @IBOutlet weak var growthFormLabel: UILabel!
    @IBOutlet weak var leafShapeLabel: UILabel!
    @IBOutlet weak var leafArrangementLabel: UILabel!
    @IBOutlet weak var petalNumberLabel: UILabel!
    @IBOutlet weak var flowerShapeLabel: UILabel!
    @IBOutlet weak var synonymsLabel: UILabel!
    @IBOutlet weak var flowerColorLabel: UILabel!
    @IBOutlet weak var commonNameLabel: UILabel!
    @IBOutlet weak var habitatLabel: UILabel!
    @IBOutlet weak var photoCreditLabel: UILabel!
    @IBOutlet weak var notesLabel: UILabel!
    @IBOutlet weak var titleLabel: UILabel!
    
    var speciesDict = [[String:AnyObject]]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if (speciesDict[0]["growth_form"] as! String) == "forb"
        {
            speciesLabel.text = "Species Name: " + (speciesDict[0]["species_name"] as! String)
            familyLabel.text = "Family: " + (speciesDict[0]["family_name"] as! String)
            growthFormLabel.text = "Growth Form: " + (speciesDict[0]["growth_form"] as! String)
            leafShapeLabel.text = "Leaf Shape: " + (speciesDict[0]["leaf_shape_filter"] as! String)
            leafArrangementLabel.text = "Leaf Arrangement: " + (speciesDict[0]["leaf_arrangement"] as! String)
            petalNumberLabel.text = "Petal Number: " + (speciesDict[0]["petal_number"] as! String)
            flowerShapeLabel.text = "Flower Shape: " + (speciesDict[0]["flower_shape"] as! String)
            synonymsLabel.text = "Synonyms: " + (speciesDict[0]["synonyms"] as! String)
            flowerColorLabel.text = "Flower Color: " + (speciesDict[0]["flower_color"] as! String)
            commonNameLabel.text = "Common Name: " + (speciesDict[0]["common_name"] as! String)
            habitatLabel.text = "Habitat: " + (speciesDict[0]["habitat"] as! String)
            /*
             photoCreditLabel.text = "Photo Credit: " + (speciesDict[0]["photo_credit"] as! String)
             */
            photoCreditLabel.text = "Photo Credit: Brian Larson"
            notesLabel.text = "Notes: " + (speciesDict[0]["notes"] as! String)
            titleLabel.text = (speciesDict[0]["species_name"] as! String)
        }
        else
        {
            speciesLabel.text = "This is broken"
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
