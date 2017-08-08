/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.registrar;

/**
 * It represents an error in backend processing and is sent back to client
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
public class RegistrarError {

    private String type;
    private String errorMessage;

    public RegistrarError(Exception e) {
        this.type = e.getClass().getSimpleName();
        this.errorMessage = e.getMessage();
    }

    public String getType() {
        return type;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
