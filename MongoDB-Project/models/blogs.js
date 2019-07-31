const mongoose = require ('mongoose');

const blogSchema = mongoose.Schema({
    title:{
        type : String,
        required : true
    },
    author :{
        type : mongoose.Schema.Types.ObjectId,
        ref : 'User',
        required : false
    },
    body:{
        type : String,
        required : true
    },
    slug:{
        type : String,
        required : true
    },
    comments:[
        {
            user_id:{
                type : String,
                required : true
            }
        ,
        
            comment:{
                type : String,
                required : true
            }
        ,
        
            approved:{
                type : Boolean,
                default : false
            }
        ,
        
            created_at:{
                type : Date,
                default : Date.now
            }
        
        }],
    category:[
        {
            name:{
                type : String,
                required : true
            }
        }
    ]
});

const Blogs = module.exports = mongoose.model('Blogs', blogSchema);

//Get All Blogs
module.exports.getBlogs = (callback, limit) => {
    Blogs.find(callback).limit(limit).populate('author');
}

//Get Blog by ID
module.exports.getBlogById = (id, callback) => {
	Blogs.findById(id).exec(callback);
}

//Add Blog
module.exports.addBlog = (blog, callback) => {
    Blogs.create(blog, callback);
}

// Edit Blog
module.exports.updateBlog = (id, blog, options, callback) => {
	Blogs.findOneAndUpdate(Blogs.findById(id), blog, options, callback);
}

// Delete Blog
module.exports.removeBlog = (id, callback) => {
	Blogs.deleteOne(Blogs.findById(id)).exec(callback);	
}