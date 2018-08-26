package com.clarencezero.mylove.mapper;


import com.clarencezero.mylove.entity.ImageBean;

import java.util.List;

public interface ImageMapper {
    List<ImageBean> getAllImage();

    int insertAImage(ImageBean bean);
}
