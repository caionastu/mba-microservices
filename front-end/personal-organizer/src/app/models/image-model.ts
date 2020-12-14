import { ImageType } from './image-tpye-model';
export interface Image {
    id: string;
    userId: string;
    image: string;
    imageType: ImageType;
}