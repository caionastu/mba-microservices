import { ImageType } from './../models/image-tpye-model';
import { PageFilter } from './page-filter';

export interface ImageFilter extends PageFilter {
    userId: string;
    imageType: ImageType;
}