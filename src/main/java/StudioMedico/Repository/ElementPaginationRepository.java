/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.Repository;

import StudioMedico.Model.Element;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface ElementPaginationRepository extends PagingAndSortingRepository<Element, Long> {

}
