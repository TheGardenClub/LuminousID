//
//  ForbsFilterTableViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/20/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

protocol ForbsFilterTableProtocol{
    func filtersWereSelected(filterList: FilterList)
}

class ForbsFilterTableViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, ForbsSpecificFilterProtocol {
    var filterDict = [[String:AnyObject]]()
    var row = 0
    var filterList = ["Inflorescence", "Petal Number", "Habitat", "Flower Color", "Flower Shape", "Leaf Shape", "Leaf Arrangement"]
    var filterFormattedList = ["inflorescence", "petal_number", "habitat", "flower_color", "flower_shape", "leaf_shape", "leaf_arrangement"]
    var selectionList = ["All", "All", "All", "All", "All", "All", "All"]
    var filterAttribute = ""
    var filterValue = ""
    
    
    var delegate:ForbsFilterTableProtocol?
    
    
    
    @IBOutlet weak var forbsFilterTable: UITableView!
    
    
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
        return filterList.count
    }
    
    func filterWasSelected(filter: FilterElement){
        filterAttribute = filter.attribute
        filterValue = filter.value
        if filterAttribute == "Inflorescence"{
            selectionList[0] = filterValue
        }
        else if filterAttribute == "Petal Number"{
            selectionList[1] = filterValue
        }
        else if filterAttribute == "Habitat"{
            selectionList[2] = filterValue
        }
        else if filterAttribute == "Flower Color"{
            selectionList[3] = filterValue
        }
        else if filterAttribute == "Flower Shape"{
            selectionList[4] = filterValue
        }
        else if filterAttribute == "Leaf Shape"{
            selectionList[5] = filterValue
        }
        else if filterAttribute == "Leaf Arrangement"{
            selectionList[6] = filterValue
        }
        forbsFilterTable.reloadData()
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "forbsFilterCell", for: indexPath) as! FilterTableViewCell

        cell.FilterLabel.text = self.filterList[indexPath.row]
        cell.SelectionLabel.text = self.selectionList[indexPath.row]

        return (cell)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        print(filterAttribute)
        print(filterValue)
        performSegue(withIdentifier: "toForbsSpecificFilters", sender: filterList[indexPath.row])
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let specificFiltersVC = segue.destination as! ForbsSpecificFiltersViewController
        specificFiltersVC.delegate = self
        specificFiltersVC.attributeName = self.filterList[row]
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
