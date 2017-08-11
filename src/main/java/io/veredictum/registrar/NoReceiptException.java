/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/
package io.veredictum.registrar;

/**
 * It gets thrown in case of no transaction receipt from Ethereum blockchain.
 * Possible reasons could be not enough gas or not high enough gas price.
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */

public class NoReceiptException extends Exception {

    public NoReceiptException(String message) {
        super(message);
    }

}
