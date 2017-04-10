//
//  GlossaryTableViewCell.swift
//  LuminousID
//
//  Created by Brian Larson on 4/10/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class GlossaryTableViewCell: UITableViewCell {

    @IBOutlet weak var GlossaryLabel: UILabel!
    @IBOutlet weak var GlossaryImageView: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
