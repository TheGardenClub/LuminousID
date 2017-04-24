//
//  SedgesFiltersViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/24/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

protocol SedgesFilterTableProtocol{
    func filtersWereSelected(filterList: FilterList)
}

class SedgesFiltersViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, SedgesSpecificFilterProtocol {
    
    var filterDict = [[String:AnyObject]]()
    var row = 0
    var filtersList = ["Stem Cross-section", "Leaf Blade", "Inflorescence", "Spike Color", "Habitat"]
    var filterFormattedList = ["stem_cross_section", "leaf_blade", "inflorescence", "spike_color", "habitat"]
    var selectionList = ["All", "All", "All", "All", "All"]
    var filterAttribute = ""
    var filterValue = ""
    
    
    var delegate:SedgesFilterTableProtocol?
    
    
    @IBOutlet weak var sedgesFilterTable: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func SearchButton(_ sender: UIButton) {
        let filters = FilterList(attributes: filterFormattedList, values: selectionList)
        self.delegate?.filtersWereSelected(filterList: filters)
        self.navigationController!.popViewController(animated: true)
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return filtersList.count
    }
    
    func filterWasSelected(filter: FilterElement){
        filterAttribute = filter.attribute
        filterValue = filter.value
        print (filterAttribute)
        print (filterValue)
        if filterAttribute == "Stem Cross-section"{
            selectionList[0] = filterValue
        }
        else if filterAttribute == "Leaf Blade"{
            selectionList[1] = filterValue
        }
        else if filterAttribute == "Inflorescence"{
            selectionList[2] = filterValue
        }
        else if filterAttribute == "Spike Color"{
            selectionList[3] = filterValue
        }
        else if filterAttribute == "Habitat"{
            selectionList[4] = filterValue
        }
        sedgesFilterTable.reloadData()
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "sedgesFilterCell", for: indexPath) as! FilterTableViewCell
        cell.FilterLabel.text = self.filtersList[indexPath.row]
        cell.SelectionLabel.text = self.selectionList[indexPath.row]
        
        return (cell)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        performSegue(withIdentifier: "toSedgesSpecificFilters", sender: filtersList[indexPath.row])
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let specificFiltersVC = segue.destination as! SedgesSpecificFiltersViewController
        specificFiltersVC.delegate = self
        specificFiltersVC.attributeName = self.filtersList[row]
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
