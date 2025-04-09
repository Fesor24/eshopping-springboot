package com.eshopping.project.service;

import com.eshopping.project.entities.BaseEntity;
import com.eshopping.project.models.requests.SearchParams;
import com.eshopping.project.models.response.BaseResponse;
import com.eshopping.project.models.response.PaginatedList;
import com.eshopping.project.primitives.Error;
import com.eshopping.project.primitives.Result;
import com.eshopping.project.primitives.ResultT;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public abstract class BaseEntityService<TResponse extends BaseResponse, TID,
        TEntity extends BaseEntity, TRepository extends JpaRepository<TEntity, TID>> {

    private final TRepository repository;
    private final ModelMapper mapper;
    private final Class<TResponse> responseType;
    private final Class<TEntity> entityType;

    public BaseEntityService(ModelMapper mapper, Class<TResponse> responseType,
                          Class<TEntity> entityType, TRepository repository) {
        this.mapper = mapper;
        this.responseType = responseType;
        this.repository = repository;
        this.entityType = entityType;
    }

    public ResultT<TResponse> getById(TID id) {
        Optional<TEntity> entity = this.repository.findById(id);

        if(entity.isEmpty())
            return Result.failure(
                    Error.NotFound("not.found",
                            entityType.getSimpleName() + " with Id: " + id.toString() + " not found"));

        TResponse response = mapper.map(entity.get(), responseType);

        return response.toResult();
    }

    public Result delete(TID id){
        Optional<TEntity> entity = this.repository.findById(id);

        if(entity.isEmpty()){
            return Result.failure(
                    Error.NotFound("not.found",
                            entityType.getSimpleName() + " with Id: " + id.toString() + " not found"));
        }

        this.repository.delete(entity.get());

        return Result.success();
    }

    public ResultT<PaginatedList<TResponse>> search(SearchParams searchParams) {
        Sort sort = searchParams.getSortOrder().equalsIgnoreCase("asc") ?
                Sort.by(searchParams.getSortBy()).ascending() :
                Sort.by(searchParams.getSortBy()).descending();

        Pageable pageDetails = PageRequest.of(
                searchParams.getPageNumber(),
                searchParams.getPageSize(), sort);

        Page<TEntity> entities = this.repository.findAll(pageDetails);

        List<TResponse> entitiesResponse = entities.getContent()
                .stream().map(entity -> this.mapper.map(entity, responseType))
                .collect(Collectors.toList());

        PaginatedList<TResponse> response = new PaginatedList<>();
        response.setContent(entitiesResponse);
        response.setPageNumber(entities.getNumber());
        response.setPageSize(entities.getSize());
        response.setTotalElements(entities.getNumberOfElements());
        response.setTotalPages(entities.getTotalPages());

        return Result.create(response);
    }
}
