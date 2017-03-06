//
//  FieldGuideTableViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 2/27/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit
import Firebase
import FirebaseDatabase
import FirebaseAuth

class ForbsTableViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    @IBOutlet weak var forbsTable: UITableView!
    
    var myDict = [[String:AnyObject]]()
    var speciesNames:[String] = []
    var handle:FIRDatabaseHandle?
    var ref:FIRDatabaseReference?
    var row = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        ref = FIRDatabase.database().reference().child("speciesid").child("field_guide").child("forbs")
        ref?.observe(.value, with: { (snapshot) in
            
            if let snapshots = snapshot.children.allObjects as? [FIRDataSnapshot]
            {
                self.myDict = snapshots.flatMap { $0.value as? [String:AnyObject]}
                for item in self.myDict{
                    self.speciesNames.append(item["species_name"] as! String)
                    self.forbsTable.reloadData()
                }

            }
        })
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return speciesNames.count
    }
    
    

    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = UITableViewCell(style: UITableViewCellStyle.default, reuseIdentifier: "fieldGuideCell")
        cell.textLabel?.text = self.speciesNames[indexPath.row]
        return(cell)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row
        performSegue(withIdentifier: "toSpeciesFromForbs", sender: speciesNames[indexPath.row])
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let speciesInfoVC = segue.destination as! SpeciesInfoViewController
        speciesInfoVC.speciesName = "Species Name: " + (myDict[row]["species_name"] as! String)
        speciesInfoVC.speciesFamily = "Family: " + (myDict[row]["family_name"] as! String)
        speciesInfoVC.speciesGrowthForm = "Growth Form: " + (myDict[row]["growth_form"] as! String)
        speciesInfoVC.speciesLeafShape = "Leaf Shape: " + (myDict[row]["leaf_shape_filter"] as! String)
        speciesInfoVC.speciesLeafArrangement = "Leaf Arrangement: " + (myDict[row]["leaf_arrangement"] as! String)
        speciesInfoVC.speciesPetalNumber = "Petal Number: " + (myDict[row]["petal_number"] as! String)
        speciesInfoVC.speciesFlowerShape = "Flower Shape: " + (myDict[row]["flower_shape"] as! String)
        speciesInfoVC.speciesSynonyms = "Synonyms: " + (myDict[row]["synonyms"] as! String)
        speciesInfoVC.speciesFlowerColor = "Flower Color: " + (myDict[row]["flower_color"] as! String)
        speciesInfoVC.speciesCommonName = "Common Name: " + (myDict[row]["common_name"] as! String)
        speciesInfoVC.speciesHabitat = "Habitat: " + (myDict[row]["habitat"] as! String)
        /*
        speciesInfoVC.speciesPhotoCredit = "Photo Credit: " + (myDict[row]["photo_credit"] as! String)
        */
        speciesInfoVC.speciesPhotoCredit = "Photo Credit: Brian Larson"
        speciesInfoVC.speciesNotes = "Notes: " + (myDict[row]["notes"] as! String)
        speciesInfoVC.speciesTitle = (myDict[row]["species_name"] as! String)
        
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
