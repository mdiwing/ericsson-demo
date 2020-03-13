import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
    tags = {
            @Tag(name="Pets", description="Pet operations.")
    },
    info = @Info(
        title="Pet API",
        version = "1.0.1",
        contact = @Contact(
            name = "Pet API Support",
            url = "http://exampleurl.com/contact",
            email = "pets@example.com"),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class PetApplication extends Application {
}