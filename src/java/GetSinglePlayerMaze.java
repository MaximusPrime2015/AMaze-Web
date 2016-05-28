
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.server.Server;
import model.server.ServerTCP;

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2016 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2016 Sun Microsystems, Inc.
 */

/**
 *
 * @author user
 */
@WebServlet(name = "GetSinglePlayerMaze", urlPatterns = {"/secure/GetSinglePlayerMaze"})
public class GetSinglePlayerMaze extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = "{\"Type\":2,\"Content\":{\"Name\":\"iM\",\"Maze\":\"222220000100*00100021112111111121111102100210100222001010211121011121011101021002222222101010002111010101011101011200101010101222222221111111111121110122220010000002001012012111110111211111201200100010120012221121111111012111210002220010022200121011012111112111112110101200101200100200011121110121111121101002222222001002000111010101011111211000001010101#222200\",\"Start\":{\"Row\":0,\"Col\":12},\"End\":{\"Row\":18,\"Col\":12}}}";
        
//        Server server = ServerTCP.getInstance();
//        String mazeName = GetRandomName();
//        server.sendRequest("generate " + mazeName + " 1");
        
        
        response.setContentType("appliation/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }
    
    private String GetRandomName() {
        String name = "maze_";
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            name += ('a' + rnd.nextInt(25));
        }
        return name;
    }
}
