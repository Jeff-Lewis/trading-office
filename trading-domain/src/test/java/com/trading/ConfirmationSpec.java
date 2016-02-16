package com.trading;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfirmationSpec {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void can_be_parsed_to_json_and_converted_back_to_object() throws Exception {

        String confirmationAsString = OBJECT_MAPPER.writeValueAsString(confirmation());

        Confirmation confirmationCreatedFromJson = OBJECT_MAPPER.readValue(
                confirmationAsString, Confirmation.class
        );

        assertThat(confirmationCreatedFromJson.getAllocationReport()).isEqualToComparingFieldByField(
                confirmation().getAllocationReport()
        );

        assertThat(confirmationCreatedFromJson.getContent()).isEqualTo(
                confirmation().getContent()
        );

        System.out.println(confirmationCreatedFromJson);
    }

    @Test
    public void has_id_derived_from_allocation_report() throws Exception {
        assertThat(confirmation().id()).isEqualTo(
                TestData.allocationReport().getAllocationId()
        );
    }

    private Confirmation confirmation() {
        Confirmation confirmation = new Confirmation();
        confirmation.setAllocationReport(TestData.allocationReport());
        confirmation.setContent("dummy confirmation content".getBytes(StandardCharsets.UTF_8));
        return confirmation;
    }
}
