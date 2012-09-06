import java.io.IOException;
import java.util.Date;

import org.apache.james.mime4j.field.address.Mailbox;
import org.apache.james.mime4j.message.BodyFactory;
import org.apache.james.mime4j.message.Message;
import org.apache.james.mime4j.message.TextBody;

import sun.misc.BASE64Encoder;

/**
 * This example generates a message very similar to the one from RFC 5322
 * Appendix A.1.1.
 */
public class Test{
    public static void main(String[] args) throws IOException {
        // 1) start with an empty message

        Message message = new Message();

        // 2) set header fields

        // Date and From are required fields
        message.setDate(new Date());
        message.setFrom(Mailbox.parse("John Doe <jdoe@machine.example>"));

        // Message-ID should be present
        message.createMessageId("machine.example");

        // set some optional fields
        message.setTo(Mailbox.parse("Mary Smith <mary@example.net>"));
        message.setSubject("Saying Hello");

        // 3) set a text body

        BodyFactory bodyFactory = new BodyFactory();
        TextBody body = bodyFactory.textBody("This is a message just to "
                + "say hello.\r\nSo, \"Hello\".");

        // note that setText also sets the Content-Type header field
        message.setText(body);

        // 4) print message to standard output

        message.writeTo(System.out);

        // 5) message is no longer needed and should be disposed of

        message.dispose();
    }
}
