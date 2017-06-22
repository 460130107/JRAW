package net.dean.jraw.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.dean.jraw.RedditClient
import net.dean.jraw.databind.DistinguishedStatusDeserializer
import net.dean.jraw.databind.UnixTimeDeserializer
import net.dean.jraw.references.CommentReference
import java.util.*

/**
 * This class represents a comment on a [Submission].
 *
 * Comments are usually created through [CommentNode]s:
 *
 * ```kotlin
 * val firstTopLevelComment: Comment = reddit.submission(id).comments().replies[0].comment
 * ```
 */
data class Comment(
    /** If this comment belongs to a Submission which has been marked as unmodifiable */
    val archived: Boolean,

    override val author: String,

    /** Flair to appear next to the creator of this comment's name, if any */
    val authorFlairText: String?,

    /** The Markdown-formatted body of this comment */
    val body: String,

    override val canGild: Boolean,

    /**
     * Get this comments controversiality level. A comment is considered controversial if it has a large number of both
     * upvotes and downvotes. 0 means not controversial, 1 means controversial.
     */
    val controversiality: Int,

    /** When this comment was created */
    @JsonProperty("created_utc")
    @JsonDeserialize(using = UnixTimeDeserializer::class)
    override val created: Date,

    @JsonDeserialize(using = DistinguishedStatusDeserializer::class)
    override val distinguished: DistinguishedStatus?,

    /** When this comment was edited, if any */
    @JsonDeserialize(using = UnixTimeDeserializer::class)
    val edited: Date?,

    @JsonProperty("name")
    override val fullName: String,
    override val gilded: Short,
    override val id: String,
    override val likes: Boolean?,
    override val saved: Boolean,
    override val score: Int,

    /** If reddit is masking the score of a new comment */
    val scoreHidden: Boolean,

    /** If this comment is shown at the top of the comment section, regardless of score */
    val stickied: Boolean,

    /** The full name of the submission that this comment is contained in (`t3_XXXXX`) */
    @JsonProperty("link_id")
    val submissionFullName: String,

    @JsonProperty("subreddit")
    override val subreddit: String,

    @JsonProperty("subreddit_id")
    override val subredditFullName: String,

    /** The restrictions for accessing this subreddit */
    val subredditType: Subreddit.Type
) : PublicContribution<CommentReference>(KindConstants.COMMENT) {
    override fun toReference(reddit: RedditClient) = CommentReference(reddit, id)
}