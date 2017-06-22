package net.dean.jraw.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * An award given to a user by reddit
 *
 * Trophies are displayed on a user's page at `/u/{username}`.
 */
data class Trophy(
    /** Trophy name (e.g. "Five-Year Club") */
    val name: String,

    /**
     * An optional URL that highlights where/how the user gained the trophy. If this URL is relative, it is relative to
     * `reddit.com`
     */
    val url: String?,

    /** The 40x40 icon for this Trophy */
    @JsonProperty("icon_40") val icon40: String,

    /** The 70x70 icon for this Trophy */
    @JsonProperty("icon_70") val icon70: String,

    /** A unique ID generated by reddit */
    val id: String?,

    /** A succinct description of how/when/why the user got this award */
    val description: String?
) : RedditObject(KindConstants.TROPHY)