package com.sivalabs.geeksclub.techfeed

import com.sivalabs.geeksclub.entities.Category
import com.sivalabs.geeksclub.entities.User

class NewLinkDTO {
    var title : String = ""
    var url : String = ""
    var tags: String = ""
    var category: Category = Category()
    var createdBy : User = User()
}