package net.dean.jraw.models

/**
 * Represents any non-root CommentNode.
 *
 * Just like RootCommentNode, creating one instance of this class recursively constructs a CommentNode for each comment
 * in the tree below this node.
 */
class ReplyCommentNode internal constructor(
    /** The data this node represents */
    val comment: Comment,
    override val depth: Int,
    replies: Map<String, Any>
) : CommentNode {
    override val moreChildren: MoreChildren? = null
    override val replies: List<ReplyCommentNode> = listOf()

    init {
        // TODO
//        // For whatever reason some engineer at reddit thought it would be a good idea to make replies an empty string
//        // if there are no replies instead of a zero-length array like a sane person.
//        this.replies = if (replies.isTextual && replies.textValue() == "") {
//            listOf()
//        } else {
//            // Find the Listing children
//            val childrenNode = replies.get("data").get("children") ?:
//                throw IllegalArgumentException("Unexpected JSON structure")
//            // Make sure reddit is throwing us the right data
//            if (!childrenNode.isArray) throw IllegalArgumentException("Expected children node to be an array")
//            // Map each JsonNode in the array to a CommentNode
//            childrenNode.map {
//                val comment: Comment = JrawUtils.jackson.treeToValue(it)
//                ReplyCommentNode(comment, depth + 1, it["data"]["replies"])
//            }
//        }
    }

    override fun toString(): String {
        return "ReplyCommentNode(comment=$comment, depth=$depth, moreChildren=$moreChildren, replies=List(size=${replies.size}))"
    }
}
