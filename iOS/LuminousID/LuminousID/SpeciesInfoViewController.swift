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
    
    var speciesName = "Null"
    var speciesFamily = "Null"
    var speciesGrowthForm = "Null"
    var speciesLeafShape = "Null"
    var speciesLeafArrangement = "Null"
    var speciesPetalNumber = "Null"
    var speciesFlowerShape = "Null"
    var speciesSynonyms = "Null"
    var speciesFlowerColor = "Null"
    var speciesCommonName = "Null"
    var speciesHabitat = "Null"
    var speciesPhotoCredit = "Null"
    var speciesNotes = "Null"
    var speciesTitle = "Null"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        speciesLabel.text = speciesName
        familyLabel.text = speciesFamily
        growthFormLabel.text = speciesGrowthForm
        leafShapeLabel.text = speciesLeafShape
        leafArrangementLabel.text = speciesLeafArrangement
        petalNumberLabel.text = speciesPetalNumber
        flowerShapeLabel.text = speciesFlowerShape
        synonymsLabel.text = speciesSynonyms
        flowerColorLabel.text = speciesFlowerColor
        commonNameLabel.text = speciesCommonName
        habitatLabel.text = speciesHabitat
        photoCreditLabel.text = speciesPhotoCredit
        notesLabel.text = speciesNotes
        titleLabel.text = speciesTitle
        
        
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
