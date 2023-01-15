package com.nihongo.admin.paging;

import com.nihongo.common.entity.PagingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public class PagingAndSortingHelper {
    private String sortField;
    private String sortDir;
    private String keyword;

    public PagingAndSortingHelper(String sortField, String sortDir, String keyword) {
        this.sortField = sortField;
        this.sortDir = sortDir;
        this.keyword = keyword;
    }

    public PagingDTO updateAttributes(int pageNum, Page<?> page) {
        List<?> listItems = page.getContent();
        int pageSize = page.getSize();

        long startCount = (pageNum - 1) * pageSize + 1;
        long endCount = startCount + pageSize - 1;
        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        return new PagingDTO(pageNum, page.getTotalPages(), (int)startCount, (int)endCount, (int)page.getTotalElements(), listItems);

//        model.addAttribute("currentPage", pageNum);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("startCount", startCount);
//        model.addAttribute("endCount", endCount);
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute(listName, listItems);
    }

    public PagingDTO listEntities(int pageNum, int pageSize, SearchRepository<?, Integer> repo) {
        Pageable pageable = createPageable(pageSize, pageNum);
        Page<?> page = null;

        if (keyword != null) {
            page = repo.findAll(keyword, pageable);
        } else {
            page = repo.findAll(pageable);
        }

        return updateAttributes(pageNum, page);
    }

    public Pageable createPageable(int pageSize, int pageNum) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        return PageRequest.of(pageNum - 1, pageSize, sort);
    }

    public String getSortField() {
        return sortField;
    }

    public String getSortDir() {
        return sortDir;
    }

    public String getKeyword() {
        return keyword;
    }
}
