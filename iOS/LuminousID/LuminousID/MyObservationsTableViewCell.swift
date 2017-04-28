//
//  MyObservationsTableViewCell.swift
//  LuminousID
//
//  Created by Brian Larson on 4/27/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class MyObservationsTableViewCell: UITableViewCell {

    @IBOutlet weak var myObsImageView: UIImageView!
    @IBOutlet weak var myObsSpeciesLabel: UILabel!
    @IBOutlet weak var myObsDateLabel: UILabel!
    @IBOutlet weak var myObsSyncedLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
