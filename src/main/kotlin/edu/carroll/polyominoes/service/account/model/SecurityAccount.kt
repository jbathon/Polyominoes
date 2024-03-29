package edu.carroll.polyominoes.service.account.model

import edu.carroll.polyominoes.jpa.model.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityAccount(private val account: Account) : UserDetails {

    /**
     * Returns the username used to authenticate the user. Cannot return
     * `null`.
     * @return the username (never `null`)
     */
    override fun getUsername(): String {
        return account.username
    }

    /**
     * Returns the password used to authenticate the user.
     * @return the password
     */
    override fun getPassword(): String {
        return account.hashPassword
    }

    /**
     * Returns the authorities granted to the user. Cannot return `null`.
     * @return the authorities, sorted by natural key (never `null`)
     */
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return HashSet<GrantedAuthority>()
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     * @return `true` if the user's account is valid (ie non-expired),
     * `false` if no longer valid (ie expired)
     */
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     * @return `true` if the user is not locked, `false` otherwise
     */
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     * @return `true` if the user's credentials are valid (ie non-expired),
     * `false` if no longer valid (ie expired)
     */
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     * @return `true` if the user is enabled, `false` otherwise
     */
    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return false
        if (o == null || o::class != this::class) {
            return false
        }
        val userDetails = o as SecurityAccount
        return (account.username == userDetails.username && account.hashPassword == userDetails.password)
    }

}