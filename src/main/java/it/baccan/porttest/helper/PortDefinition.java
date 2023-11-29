/* 
 * This file is part of the PortTest distribution (https://github.com/matteobaccan/PortTest).
 * Copyright (c) 2021 Matteo Baccan
 * 
 * This program is free software: you can redistribute it and/or modify  
 * it under the terms of the GNU General Public License as published by  
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License 
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
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
