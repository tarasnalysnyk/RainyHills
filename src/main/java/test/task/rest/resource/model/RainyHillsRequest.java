package test.task.rest.resource.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RainyHillsRequest")
public class RainyHillsRequest {

    private Integer[] array;

    @Schema(required = true, example = "[3, 2, 4, 1, 2]")
    public Integer[] getArray() {
        return array;
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }
}
