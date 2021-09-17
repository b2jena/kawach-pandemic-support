export class Content{
    contentId!: any;
    domain!: string;
    title!: string;
    language!: string;
    likes!: number;
    shares!: number;
    comments!: Map<string, string>;
    instructions!: string;
    uploadedBy!: string;
}
