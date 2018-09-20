
multibranchPipelineJob('forjj-bitbucket') {
  description('Folder for Project forjj-bitbucket generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          repoOwner('forj-oss')
          scanCredentialsId('github-user')
          repository('forjj-bitbucket')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
