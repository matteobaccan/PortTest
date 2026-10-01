/* 
 * This file is part of the PortTest distribution (https://github.com/matteobaccan/PortTest).
 * Copyright (c) 2021 Matteo Baccan
 *
 * Licensed under the MIT License. See the LICENSE file in the project root
 * for the full license text.
 */
package it.baccan.porttest.pojo;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Matteo Baccan
 */
@Data
public class Port {
    private String name;
    private Date releaseDate;
    private List<PortDetail> portDetails;
}
