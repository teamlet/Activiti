/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.rest.api.repository;

import java.io.ByteArrayInputStream;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.repository.Model;
import org.activiti.rest.api.ActivitiUtil;
import org.restlet.data.MediaType;
import org.restlet.representation.InputRepresentation;


/**
 * @author Frederik Heremans
 */
public class ModelSourceExtraResource extends BaseModelSourceResource {

  @Override
  protected InputRepresentation getModelStream(Model model) {
    byte[] editorSource = ActivitiUtil.getRepositoryService().getModelEditorSourceExtra(model.getId());
    if(editorSource == null) {
      throw new ActivitiObjectNotFoundException("Model with id '" + model.getId() + "' does not have extra source available.", String.class);
    }
    return new InputRepresentation(new ByteArrayInputStream(editorSource), MediaType.APPLICATION_OCTET_STREAM);
  }

  @Override
  protected void setModelSource(Model model, byte[] byteArray) {
    ActivitiUtil.getRepositoryService().addModelEditorSourceExtra(model.getId(), byteArray);
  }
  
}
