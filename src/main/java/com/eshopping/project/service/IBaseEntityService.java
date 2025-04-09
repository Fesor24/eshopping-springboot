package com.eshopping.project.service;

import com.eshopping.project.models.requests.SearchParams;
import com.eshopping.project.models.response.BaseResponse;
import com.eshopping.project.models.response.PaginatedList;
import com.eshopping.project.primitives.Result;
import com.eshopping.project.primitives.ResultT;

public interface IBaseEntityService <TResponse extends BaseResponse, TID>{
    Result delete(TID id);
    ResultT<PaginatedList<TResponse>> search(SearchParams searchParams);
    ResultT<TResponse> getById(TID id);
}
