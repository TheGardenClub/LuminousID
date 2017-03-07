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
            Label2.text = "Common Name: " + (speciesDict[0]["common_name"] as! String)
            Label3.text = "Synonyms: " + (speciesDict[0]["synonyms"] as! String)
            Label4.text = "Growth Form: " + (speciesDict[0]["growth_form"] as! String)
            Label5.text = "Family Name: " + (speciesDict[0]["family_name"] as! String)
            Label6.text = "Leaf Shape: " + (speciesDict[0]["leaf_shape_filter"] as! String)
            Label7.text = "Leaf Arrangement: " + (speciesDict[0]["leaf_arrangement"] as! String)
            Label8.text = "Petal Number: " + (speciesDict[0]["petal_number"] as! String)
            Label9.text = "Flower Shape: " + (speciesDict[0]["flower_shape"] as! String)
            Label10.text = "Flower Color: " + (speciesDict[0]["flower_color"] as! String)
            Label11.text = "Habitat: " + (speciesDict[0]["habitat"] as! String)
            /*
             Label12.text = "Photo Credit: " + (speciesDict[0]["photo_credit"] as! String)
             */
            Label12.text = "Photo Credit: Brian Larson"
            Label13.text = "Notes: " + (speciesDict[0]["notes"] as! String)
            titleLabel.text = (speciesDict[0]["species_name"] as! String)
        }
        else if (speciesDict[0]["growth_form"] as! String) == "shrub/tree"
        {
            if (speciesDict[0]["leaf_type"] as! String) == "deciduous"
            {
                Label1.text = "Species Name: " + (speciesDict[0]["species_name"] as! String)
                Label2.text = "Common Name: " + (speciesDict[0]["common_name"] as! String)
                Label3.text = "Synonyms: " + (speciesDict[0]["synonyms"] as! String)
                Label4.text = "Growth Form: " + (speciesDict[0]["growth_form"] as! String)
                Label5.text = "Family Name: " + (speciesDict[0]["family_name"] as! String)
                Label6.text = "Leaf Type: " + (speciesDict[0]["leaf_type"] as! String)
                Label7.text = "Leaf Shape: " + (speciesDict[0]["leaf_shape"] as! String)
                Label8.text = "Leaf Arrangement: " + (speciesDict[0]["leaf_arrangement"] as! String)
                Label9.text = "Leaf Margin: " + (speciesDict[0]["leaf_margin"] as! String)
                Label10.text = "Cone: " + (speciesDict[0]["cone"] as! String)
                /*
                 Label11.text = "Photo Credit: " + (speciesDict[0]["photo_credit"] as! String)
                 */
                Label11.text = "Photo Credit: Brian Larson"
                Label12.text = "Notes: " + (speciesDict[0]["notes"] as! String)
                titleLabel.text = (speciesDict[0]["species_name"] as! String)
            }
            else if (speciesDict[0]["leaf_type"] as! String) == "needle"
            {
                Label1.text = "Species Name: " + (speciesDict[0]["species_name"] as! String)
                Label2.text = "Common Name: " + (speciesDict[0]["common_name"] as! String)
                Label3.text = "Synonyms: " + (speciesDict[0]["synonyms"] as! String)
                Label4.text = "Growth Form: " + (speciesDict[0]["growth_form"] as! String)
                Label5.text = "Family Name: " + (speciesDict[0]["family_name"] as! String)
                Label6.text = "Leaf Type: " + (speciesDict[0]["leaf_type"] as! String)
                Label7.text = "Needle Apex: " + (speciesDict[0]["needle_apex"] as! String)
                Label8.text = "Needle Arrangement: " + (speciesDict[0]["needle_arrangement"] as! String)
                Label9.text = "Needles Per Fascile: " + (speciesDict[0]["needle_per_fascile"] as! String)
                Label10.text = "Cone: " + (speciesDict[0]["cone"] as! String)
                /*
                 Label11.text = "Photo Credit: " + (speciesDict[0]["photo_credit"] as! String)
                 */
                Label11.text = "Photo Credit: Brian Larson"
                Label12.text = "Notes: " + (speciesDict[0]["notes"] as! String)
                titleLabel.text = (speciesDict[0]["species_name"] as! String)
            }
        }
        else if (speciesDict[0]["growth_form"] as! String) == "graminoid"
        {
            if (speciesDict[0]["family_name"] as! String) == "Cyperaceae (Sedges)"
            {
                Label1.text = "Species Name: " + (speciesDict[0]["species_name"] as! String)
                Label2.text = "Common Name: " + (speciesDict[0]["common_name"] as! String)
                Label3.text = "Synonyms: " + (speciesDict[0]["synonyms"] as! String)
                Label4.text = "Growth Form: " + (speciesDict[0]["growth_form"] as! String)
                Label5.text = "Family Name: " + (speciesDict[0]["family_name"] as! String)
                Label6.text = "Leaf Blade: " + (speciesDict[0]["leaf_blade"] as! String)
                Label7.text = "Inflorescence: " + (speciesDict[0]["inflorescence"] as! String)
                Label8.text = "Spike Color: " + (speciesDict[0]["spike_color"] as! String)
                Label9.text = "Stem Cross Section: " + (speciesDict[0]["stem_cross_section"] as! String)
                Label10.text = "Habitat: " + (speciesDict[0]["habitat"] as! String)
                /*
                 Label11.text = "Photo Credit: " + (speciesDict[0]["photo_credit"] as! String)
                 */
                Label11.text = "Photo Credit: Brian Larson"
                Label12.text = "Notes: " + (speciesDict[0]["notes"] as! String)
                titleLabel.text = (speciesDict[0]["species_name"] as! String)
            }
            else if (speciesDict[0]["family_name"] as! String) == "Juncaceae (Rushes)"
            {
                Label1.text = "Species Name: " + (speciesDict[0]["species_name"] as! String)
                Label2.text = "Common Name: " + (speciesDict[0]["common_name"] as! String)
                Label3.text = "Synonyms: " + (speciesDict[0]["synonyms"] as! String)
                Label4.text = "Growth Form: " + (speciesDict[0]["growth_form"] as! String)
                Label5.text = "Family Name: " + (speciesDict[0]["family_name"] as! String)
                Label6.text = "Leaf Blade: " + (speciesDict[0]["leaf_blade"] as! String)
                Label7.text = "Stem Cross Section: " + (speciesDict[0]["stem_cross_section"] as! String)
                Label8.text = "Habitat: " + (speciesDict[0]["habitat"] as! String)
                /*
                 Label9.text = "Photo Credit: " + (speciesDict[0]["photo_credit"] as! String)
                 */
                Label9.text = "Photo Credit: Brian Larson"
                Label10.text = "Notes: " + (speciesDict[0]["notes"] as! String)
                titleLabel.text = (speciesDict[0]["species_name"] as! String)
            }
            else if (speciesDict[0]["family_name"] as! String) == "Poaceae (grasses)"
            {
                Label1.text = "Species Name: " + (speciesDict[0]["species_name"] as! String)
                Label2.text = "Common Name: " + (speciesDict[0]["common_name"] as! String)
                Label3.text = "Synonyms: " + (speciesDict[0]["synonyms"] as! String)
                Label4.text = "Growth Form: " + (speciesDict[0]["growth_form"] as! String)
                Label5.text = "Family Name: " + (speciesDict[0]["family_name"] as! String)
                Label6.text = "Leaf Blade: " + (speciesDict[0]["leaf_blade"] as! String)
                Label7.text = "Awns: " + (speciesDict[0]["awns"] as! String)
                Label8.text = "Inflorescence: " + (speciesDict[0]["inflorescence"] as! String)
                Label9.text = "Florets Per Spikelet: " + (speciesDict[0]["florets_per_spikelet"] as! String)
                Label10.text = "Stem Cross Section: " + (speciesDict[0]["stem_cross_section"] as! String)
                Label11.text = "Habitat: " + (speciesDict[0]["habitat"] as! String)
                /*
                 Label11.text = "Photo Credit: " + (speciesDict[0]["photo_credit"] as! String)
                 */
                Label12.text = "Photo Credit: Brian Larson"
                Label13.text = "Notes: " + (speciesDict[0]["notes"] as! String)
                
                titleLabel.text = (speciesDict[0]["species_name"] as! String)
            }
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
