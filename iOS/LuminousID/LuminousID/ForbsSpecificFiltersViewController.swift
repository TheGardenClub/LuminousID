//
//  ForbsSpecificFiltersViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/22/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

protocol ForbsSpecificFilterProtocol{
    func filterWasSelected(filter: FilterElement)
}

class ForbsSpecificFiltersViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    var attributeName = ""
    var attributeFilters = [String]()
    var row = 0

    
    var delegate:ForbsSpecificFilterProtocol?
    
    override func viewDidLoad() {

        if attributeName == "Inflorescence"{
            attributeFilters = ["All", "corymbiform", "cyme", "head", "helicoid", "paniculate", "racemose", "solitary", "spicate", "unbellate", "other"]
        }
        else if attributeName == "Petal Number"{
            attributeFilters = ["All", "3", "4", "5", "6", "6+"]
        }
        else if attributeName == "Habitat"{
            attributeFilters = ["All", "fellfield", "dry meadow", "moist meadow", "wet meadow", "snowbed", "subalpine"]
        }
        else if attributeName == "Flower Color"{
            attributeFilters = ["All", "white", "yellow", "orange", "red", "pink"]
        }
        else if attributeName == "Flower Shape"{
            attributeFilters = ["All", "campanulate", "composite", "cruciform", "funnelform", "labiate", "papilionaceous", "radial", "reflexed", "rotate", "salverform", "stellate", "urceolate", "other"]
        }
        else if attributeName == "Leaf Shape"{
            attributeFilters = ["All", "dissected", "oblong", "palmate", "ternate", "other"]
        }
        else if attributeName == "Leaf Arrangement"{
            attributeFilters = ["All", "alternate", "basal", "cauline", "opposite", "rosette", "whorled", "other"]
        }
        
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return attributeFilters.count
    }
    
    
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "forbsSpecificFilterCell", for: indexPath) as! ForbsSpecificCellTableViewCell
        cell.AttributeLabel.text = self.attributeFilters[indexPath.row]
        cell.AttributeImage.image = UIImage(named: "forbs_glossary_small_keyed/" + self.attributeFilters[indexPath.row] + ".png")
        return (cell)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        let filterChoice = FilterElement(attribute: attributeName, value: attributeFilters[indexPath.row])
        self.delegate?.filterWasSelected(filter: filterChoice)
        self.navigationController!.popViewController(animated: true)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {

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
