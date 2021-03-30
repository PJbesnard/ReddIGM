export class CreateSubcommentPayload {
    text: string = "";
    postId: number = 0;
    superCommentId!: number;

}