//
//  ForbsGlossaryViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/10/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

/*
    This view manages a table view of all of the Forbs glossary terms. Unlike th eother table views, all options and their photos are hardcoded in lists.
 */
class ForbsGlossaryViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    var row = 0
    @IBOutlet weak var forbsGlossaryTable: UITableView!
    
    let forbsGlossaryList = ["Alternate", "Basal", "Campanulate", "Composite", "Cushion", "Funnelform", "Labiate", "Oblong", "Opposite", "Palmate", "Papilionaceous", "Radial", "Reflexed", "Round", "Ternate", "Urceolate", "Whorled"]
    let forbsGlossaryImageList = ["alternate.png", "basal.png", "campanulate.png", "composite.png", "cushion.png", "funnelform.png", "labiate.png", "oblong.png", "opposite.png", "palmate.png", "papilionaceous.png", "radial.png", "reflexed.png", "round.png", "ternate.png", "urceolate.png", "whorled.png"]
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return (forbsGlossaryList.count)
    }
    
    /*
        Defines the appearance of each cell, including name of glossary item and its picture.
    */
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "forbsGlossaryCell", for: indexPath) as! GlossaryTableViewCell
        if indexPath.row <= forbsGlossaryList.count{
            cell.GlossaryImageView.image = UIImage(named: "forbs_glossary_small_keyed/" + forbsGlossaryImageList[indexPath.row])
        }
        else{
            print("End of Table Error Handled.")
        }
        cell.GlossaryLabel.text = forbsGlossaryList[indexPath.row]
        return (cell)
    }
    
    /*
        Defines the behavior when an option is selected. In this case, a segue to the Glossary Info VC
    */
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        performSegue(withIdentifier: "toGlossaryInfoFromForbs", sender: forbsGlossaryList[indexPath.row])
    }
    
    /*
        prepares the Glossary Info VC with the appropriate selection information
    */
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let glossaryInfoVC = segue.destination as! GlossaryInfoViewController
        glossaryInfoVC.imageFile = ("forbs_glossary_small_keyed/" + forbsGlossaryImageList[row])
        glossaryInfoVC.imageName = (forbsGlossaryList[row])
    }
}
