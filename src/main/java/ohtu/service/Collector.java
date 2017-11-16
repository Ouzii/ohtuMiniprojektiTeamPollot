/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kbvalto
 */
public interface Collector {
    Object collect(ResultSet rs) throws SQLException;
}
