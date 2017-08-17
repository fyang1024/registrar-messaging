/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.registrar;

import org.web3j.protocol.core.Response;

/**
 * Wrapper of Ethereum error
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
class TransactionException extends Exception {

    TransactionException(Response.Error error) {
        super(error.getMessage());
    }
}
