//
//  FieldGuideTableViewCell.swift
//  LuminousID
//
//  Created by Brian Larson on 3/20/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class FieldGuideTableViewCell: UITableViewCell {

    @IBOutlet weak var commonNameCellLabel: UILabel!
    @IBOutlet weak var speciesPhoto: UIImageView!
    @IBOutlet weak var speciesNameCellLabel: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
