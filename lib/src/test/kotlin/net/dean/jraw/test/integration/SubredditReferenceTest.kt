package net.dean.jraw.test.integration

import com.winterbe.expekt.should
import net.dean.jraw.models.SubmissionKind
import net.dean.jraw.test.SharedObjects
import net.dean.jraw.test.TestConfig.reddit
import net.dean.jraw.test.assume
import net.dean.jraw.test.ignoreRateLimit
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*

class SubredditReferenceTest : Spek({
    describe("submit") {
        val now = Date()
        val ref = reddit.subreddit("jraw_testing2")

        // submittedSelfPost is a lazily-initiated object that is created by attempting to submit a self post. All we
        // have to do is access it.
        assume({ SharedObjects.submittedSelfPost != null }, description = "should be able to submit a self post", body = {})

        it("should be able to submit a link") {
            ignoreRateLimit {
                val id = ref.submit(SubmissionKind.LINK, "test link post", "http://example.com/${now.time}", sendReplies = false)
                reddit.submission(id).inspect().id.should.equal(id)
            }
        }
    }

    describe("subscribe/unsubscribe") {
        it("should subscribe the user to the specific subreddit") {
            val pics = reddit.subreddit("pics")
            pics.subscribe()
            pics.about().isUserIsSubscriber.should.be.`true`
            pics.unsubscribe()
            pics.about().isUserIsSubscriber.should.be.`false`
        }
    }
})