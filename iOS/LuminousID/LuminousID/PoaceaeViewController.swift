//
//  PoaceaeViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 2/27/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit
import Firebase
import FirebaseDatabase
import FirebaseAuth

class PoaceaeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    var myDict = [[String:AnyObject]]()
    var speciesNames:[String] = []
    var handle:FIRDatabaseHandle?
    var ref:FIRDatabaseReference?
    var row = 0
    
    @IBOutlet weak var poaceaeTable: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        ref = FIRDatabase.database().reference().child("speciesid").child("field_guide").child("graminoids").child("poaceae")
        ref?.observe(.value, with: { (snapshot) in
            
            if let snapshots = snapshot.children.allObjects as? [FIRDataSnapshot]
            {
                self.myDict = snapshots.flatMap { $0.value as? [String:AnyObject]}
                for item in self.myDict{
                    self.speciesNames.append(item["species_name"] as! String)
                }
                
            }
            self.poaceaeTable.reloadData()
            
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
        let cell = tableView.dequeueReusableCell(withIdentifier: "poaceaeCell", for: indexPath) as! FieldGuideTableViewCell
        if indexPath.row <= (myDict.count - 1){
            cell.speciesNameCellLabel.text = self.speciesNames[indexPath.row]
            cell.commonNameCellLabel.text = myDict[indexPath.row]["common_name"] as? String
            let plantCodeString = myDict[indexPath.row]["plant_code"] as! String?
            cell.speciesPhoto.image = UIImage(named: "Images/" + plantCodeString! + "_1.jpg")
        }
        else{
            print("End of Table Error Handled.")
        }
        return (cell)
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        performSegue(withIdentifier: "toSpeciesFromPoaceae", sender: speciesNames[indexPath.row])
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let speciesInfoVC = segue.destination as! SpeciesInfoViewController
        speciesInfoVC.speciesDict = [myDict[row]]
        
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
