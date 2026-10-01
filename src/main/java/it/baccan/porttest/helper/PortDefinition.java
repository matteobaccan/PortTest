/* 
 * This file is part of the PortTest distribution (https://github.com/matteobaccan/PortTest).
 * Copyright (c) 2021 Matteo Baccan
 *
 * Licensed under the MIT License. See the LICENSE file in the project root
 * for the full license text.
 */
package it.baccan.porttest.helper;

import com.esotericsoftware.yamlbeans.YamlReader;
import it.baccan.porttest.pojo.Port;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Matteo Baccan
 */
@Slf4j
public class PortDefinition {

    @Getter
    static Port portData;

    /**
     * Hi public constructor.
     */
    private PortDefinition() {
    }

    static {
        try (InputStream inputStream = PortDefinition.class
                .getClassLoader()
                .getResourceAsStream("port.yaml")) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            try (YamlReader reader = new YamlReader(inputStreamReader)) {
                portData = reader.read(Port.class);
            }
        } catch (FileNotFoundException ex) {
            log.info("Error loading definitions", ex);
        } catch (IOException ex) {
            log.info("Error loading definitions", ex);
        }
    }

}
