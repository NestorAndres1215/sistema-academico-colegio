export type SearchResultAction =
  | 'message'
  | 'viewProfile'
  | 'viewContract'
  | 'download'
  | 'activate'
  | 'deactivate'
  | 'blocked'
  | 'viewObservation'
  | 'closeSession';

export interface SearchResultActionConfig {
  icon: string;
  variant: 'primary' | 'secondary' | 'success' | 'danger';
}